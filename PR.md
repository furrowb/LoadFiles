# PR comments

Overall, the goal was to allow adding new parsers and validators simple and easy.
I created the abstract class `LoadFileParser` to help consolidate the parser logic.
The only public method (`parse()`) will perform all the necessary parsing and validation necessary so long as the extending parsers implement the `parseLoad()` method.

I changed the original solution from using a Reader to just passing the path as it allowed for more flexibility for each individual parser while still maintaining a consistent approach. 
I also have the validators being added to the parsers at initialization to also offer 1) Flexibility and 2) Testability.
However, this does make the `AutoCloseable` implementation awkward when there's nothing to close. For example, the XLF parser uses JAXB and has nothing available to close.
While using a string for the path allows for flexibility, it also removes some consistency due to now each parser is allowed implement their solution in any fashion they choose.
This could cause some issues later if major refactoring becomes necessary and you can't consolidate the parsing logic as easily because every parser is implemented so differently.

The `ParserFactory` was created to handle parser selection based on the file extensions and to have a single way to retrieve them.
The current way it retrieves the file extension is not incredibly robust but will work for your basic extensions, so long as they aren't something like `tar.gz`.
I attempted to find a simple library for handling file extension extraction but most of them were even more rudimentary than my implementation.
It was also created to have a simple form of dependency injection that wasn't using something heavy like Spring.

The validators should be based on the `LoadFileResultValidator` so they can be added to the individual parser's collection of validators. 
It uses the data class `ValidatorResult` so that it's not a simple pass/fail but can give meaningful feedback as to why it may have failed.
This kind of takes after what both Go and Rust have for error handling.
Adding a validator to a parser is as easy as adding them to a list in the `ParserFactory`.

There are still some improvements that could be made. For example:
1) A consistent way of handling non-existent files between the parsers
1) Improve test coverage. While it's around 80+% of LOC, some additional error conditions should be tested, like directory/filename path handling from the load file, handling of null/empty values, handling of missing fields in the load file
1) Use a dependency injection library to clean up some of the handling of manually injecting objects
1) Add a logger, like slf4j
1) Log errors instead of throwing exceptions to allow execution to continue
1) Wrap all parsing libraries so they can be swapped out/reused. For example, if I need to parse an XML-like structure again, it would be useful to have a single implementation that has just an interface instead of reimplementing it for each parser. Also, if for whatever reason JAXB turns out to be not the best solution, all you'd need to do is create a new concrete implementation and inject that instead of ripping out all the old logic
1) More comments/docs for the more public facing classes
1) Make validators be a lambda rather than an interface so they can be more easily created
