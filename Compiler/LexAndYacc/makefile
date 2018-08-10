a.exe: y.tab.c lex.yy.c y.tab.h
	gcc lex.yy.c y.tab.c

lex.yy.c: lex.test.l y.tab.h
	flex lex.test.l

y.tab.c: yacc.test.y
	bison -y -d yacc.test.y
  
clean:
	rm a.exe lex.yy.c y.tab.c y.tab.h
  