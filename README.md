# Loadfiles

## Context

The Customer's customers can import pre-processed documents into the Customer's database by providing an archive in either ZIP or TAR format. The archive must contain a "loadfile" as well as the actual documents to be imported. 

A loadfile contains case-specific information about documents that we need to track, including:
  - Control numbers used to uniquely identify documents before a court
  - Volume names that group documents into collections
  - A relative path to the actual document provided in the corresponding archive.

## Build and Test

``` shell
$ gradle run

> Task :run
usage: gradle run --args 'path/to/loadfile1.opt path/to/loadfile2.opt'
```

You can see a usage message is printed. Try `gradle run --args 'src/test/resources/test.opt'`:
