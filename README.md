# dsl-project

[![Build Status](https://travis-ci.org/CPSC-410-Future-Gadget-Laboratory/dsl-project.svg?branch=master)](https://travis-ci.org/CPSC-410-Future-Gadget-Laboratory/dsl-project)

This is the CPSC 410 - Advanced Software Engineering group project.

## EBNF
Below is the EBNF of the language.
```
Program ::= EndpointDeclaration
EndpointDeclaration ::= RequestMethod "{" URLDeclaration (Statement)* Response "}"
RequestMethod ::= "GET" | "PUT" | "POST" | "DELETE"
URLDeclaration ::= String ";"
Statement ::= Conditional | ValueDeclaration | Response ";"
Response ::= "SEND = {" (Statement)* "}"
Conditional ::= "IF (" Expression ") THEN {" (Statement)* "} ELSE {" (Statement)* "}"
ValueDeclaration ::= Type identifier " = " Expression
Value::= String | Number | Boolean
Type ::= "String" | "Number" | "Boolean"
String ::= "\"" /^[a-z\d\-_\s]+$/i "\"";
Number ::= "^[0-9]+$"
Boolean ::= "true" | "false"
Expression ::= BinaryOperation | Value ";"
BinaryOperation ::= Expression BinaryOperator Expression
BinaryOperator ::=  "+" "-" "*" "/"
ReservedKeywords ::= RequestMethod & "SEND" & Type & "GET_PARAM"
```

## Language Design Specification Changelist
#### 0.0.1 - October 21st, 2019 (latest)
- `VAL` must be declared before being used.
- Endpoint Declaration must declare the endpoint url in their body using the `ENDPOINT` keyword which is assigned to a value of type `String`, and AT LEAST one response declaration using `SEND` keyword. For instance:
```
GET = {
    ENDPOINT = "/path/to/resource";
    // SOME LOGIC...
    SEND = {
        200;
        "Here is your resource!";
    }
}
```

## Contribution guide

Please do not work directly in the master branch. Work in seperate branches, and make sure that the code builds on travis before sending in a pull request to merge into master.

We will be following the peer-review method of development, so any merge into master will require atleast one review from someone. This keeps the sanity of our codebase in check, and atleast one person from the team will be upto speed with what you did.

Also, keep in mind the following things when sending in pull requests:

1. Pull request sizes should be as small as possible
2. Whenever it is possible, break pull requests into smaller ones
3. The pull request should do only one thing
4. Make a self-explanatory title describing **what** the pull request does
5. Detail with **what** was changed, **why** it was changed, and **how** it was changed.


Following these practices will ensure that:

1. Your pull request will be reviewed quickly
2. Less bugs will be introduced in the codebase
3. Development process will speed up as a result, and we'll be done before we know it
