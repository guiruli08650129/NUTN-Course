Game
===

This is a simple game.

Class
---
#### Warrior:
- 3 attributes: Name, Life and Magic.
- Method: NewMoon(新月劍法)
- Attack Target:
    - Warrios: life -25
    - Master: life -40
- Initialization:
    - Life = 400
    - Magic = 100
- Each attack consume 10 magic.
- If magic < 10 then the attack is invalid and print the message.
- If Life <= 0 then print the character name and the message of death.


#### Master:
- 3 attributes: Name, Life and Magic.
- Method: SmallFire(小火球)
- Attack Target:
    - Warrios: life -40
    - Master: life -60
- Initialization:
    - Life = 280
    - Magic = 200
- Each attack consume 25 magic.
- If magic < 25 then the attack is invalid and print the message.

- If Life <= 0 then print the character name and the message of death.

Description
---
1. Create a warrior array with 3 warriors and a master array with 3 masters. 
2. Pick 1 warrior and 1 master randomly. The warrior use NewMoon to attack master once.
3. Pick 1 warrior and 1 master randomly. The master use SmallFire to attack warrior once.
4. Repeat Step 2 and step 3 until there is one character is dead.