/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_Y_TAB_H_INCLUDED
# define YY_YY_Y_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    PGON = 258,
    PGOFF = 259,
    VOID = 260,
    INT = 261,
    FLOAT = 262,
    CHAR = 263,
    DOUBLE = 264,
    RETURN = 265,
    FOR = 266,
    WHILE = 267,
    IF = 268,
    ELSE = 269,
    ELSEIF = 270,
    PRINTF = 271,
    NUM = 272,
    ID = 273,
    DOT = 274,
    AND = 275,
    OR = 276,
    LE = 277,
    GE = 278,
    EQ = 279,
    NE = 280,
    LT = 281,
    GT = 282
  };
#endif
/* Tokens.  */
#define PGON 258
#define PGOFF 259
#define VOID 260
#define INT 261
#define FLOAT 262
#define CHAR 263
#define DOUBLE 264
#define RETURN 265
#define FOR 266
#define WHILE 267
#define IF 268
#define ELSE 269
#define ELSEIF 270
#define PRINTF 271
#define NUM 272
#define ID 273
#define DOT 274
#define AND 275
#define OR 276
#define LE 277
#define GE 278
#define EQ 279
#define NE 280
#define LT 281
#define GT 282

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif

/* Location type.  */
#if ! defined YYLTYPE && ! defined YYLTYPE_IS_DECLARED
typedef struct YYLTYPE YYLTYPE;
struct YYLTYPE
{
  int first_line;
  int first_column;
  int last_line;
  int last_column;
};
# define YYLTYPE_IS_DECLARED 1
# define YYLTYPE_IS_TRIVIAL 1
#endif


extern YYSTYPE yylval;
extern YYLTYPE yylloc;
int yyparse (void);

#endif /* !YY_YY_Y_TAB_H_INCLUDED  */
