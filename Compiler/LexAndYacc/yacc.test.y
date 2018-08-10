%{
#include <stdio.h>
#define YYSTYPE double
extern int yylex();
extern int yylineno;
int yyerror(char*);
%}

%locations
%token PGON PGOFF
%token VOID INT FLOAT CHAR DOUBLE
%token RETURN
%token FOR WHILE 
%token IF ELSE ELSEIF PRINTF 
%token NUM ID
%token DOT

%right '='
%left AND OR
%left '<' '>' LE GE EQ NE LT GT

%%
program:        goal ;

goal:           PGON stmtlist PGOFF ;

stmtlist:       stmtlist stmt | ;

stmt:           declaration ';'| whilestmt | forstmt | ifstmt | printfunc | fuction ;

fuction:        type ID '(' argInlist ')' '{' stmtlist RETURN factor ';' '}' | VOID ID '(' argInlist ')' '{' stmtlist '}' ;

argInlist:      argInlist ',' declaration | declaration | ;

declaration:    type assignment | assignment | error ;

type:           INT | FLOAT | CHAR | DOUBLE ;

assignment:     ID '=' expression | 
                ID '[' expression ']' '=' '{' arglist '}' | 
                ID '[' ']' '=' '{' arglist '}' |
                ID '[' expression ']' '=' expression |
                ID '[' ']' | 
                ID ;

expression:     term | expression '+' term | expression '-' term ;

term:           factor | term '*' factor | term '/' factor ;

factor:         ID | ID '[' expression ']' | functioncall | NUM | group ;

functioncall:   ID '(' arglist ')';

group:          '(' expression ')' ;

arglist:        arglist ',' expression | expression | ;

whilestmt:      WHILE '(' logic_expr ')' '{' stmtlist '}' ;

forstmt:        FOR '(' declaration ';' logic_expr ';' assignment ')' '{' stmtlist '}' | 
                FOR '(' logic_expr ')' '{' stmtlist '}' ;

ifstmt:         IF '(' logic_expr ')' '{' stmtlist '}' |
                IF '(' logic_expr ')' '{' stmtlist '}' ELSE '{' stmtlist '}' |
                IF '(' logic_expr ')' '{' stmtlist '}' elseifstmt ELSE '{' stmtlist '}';
                
elseifstmt:     ELSEIF '(' logic_expr ')' '{' stmtlist '}' |
                elseifstmt ELSEIF '(' logic_expr ')' '{' stmtlist '}' ;

logic_expr:     expression LE expression | 
                expression GE expression | 
                expression NE expression | 
                expression EQ expression | 
                expression GT expression | 
                expression LT expression ;

printfunc:      PRINTF '(' expression ')' ';' ;
%%

int yyerror(char *s) {
    fprintf(stderr, "line %d: %s\n", yylineno, s);
}

int main(int argc, char** argv)
{
    yyparse();
    return 0;
}
