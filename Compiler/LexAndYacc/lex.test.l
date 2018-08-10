%{
#include "y.tab.h"
#include <stdlib.h>
%}

%option yylineno
alpha [a-zA-Z]
digit [0-9]

%%
[ \t]	    ;
[ \n]       ;
pgon        return PGON;
pgoff       return PGOFF;
void        return VOID;
return      return RETURN;
int	        return INT;
float       return FLOAT;
char        return CHAR;
double      return DOUBLE;
for 	    return FOR;
while	    return WHILE;
if	        return IF;
else	    return ELSE;
elseif      return ELSEIF;
printf      return PRINTF;
{digit}+    return NUM;
[0-9]+"."[0-9]+              return NUM;
{alpha}({alpha}|{digit})*    return ID;
"<="        return LE;
">="        return GE;
"=="        return EQ;
"!="        return NE;
">"	        return GT;
"<"	        return LT;
"."         return DOT;
\/\/.* ;
\/\*(.*\n)*.*\*\/ ;
.           return yytext[0];						{ return yytext[0]; }

%%

int yywrap()
{
	return 1;
}
