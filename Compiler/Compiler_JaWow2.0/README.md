Compiler
===

Description
---
- Architecture:
    - CodeScanner
    - LexicalParser
    - SyntaxParser
    - Symbol Table
    - CodeGenerator
- Grammer:
    1. <program> =>             begin <statement list> end
    2. <statement list> => <statement> { <statement> }
    3. <statement> =>         ID := <expression> ;
    4. <statement> =>         read ( <id list> ) ;
    5. <statement> =>         write ( <expr list> ) ;
    6. <id list> =>                   ID { , ID }
    7. <expr list> =>              <expression> { , <expression> }
    8. <expression> =>       <primary> { <add op> <primary> }
    9. <primary> =>             ( <expression> )
    10. <primary> =>            ID
    11. <primary> =>            INTLITERAL
    12. <add op> =>              +
    13. <add op> =>               -
    14. <system goal> =>   <program> SCANEOF


