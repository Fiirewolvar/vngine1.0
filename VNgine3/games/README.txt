BASIC COMMANDS

Command: char
Usage: char "<id>" "<name>" "<spritelocation>"
Creates a new character with id <id> and name <name> and sets the sprite to the image found at <spritelocation>.

Command: dia
Usage: dia "<id>" "<dialogue>"
Makes the character with name <charname> say <dialogue>.

Command: sprite
Usage: sprite "<id>" "<spritelocation>"
Sets the character with name <charname>'s sprite to the image found at <spritelocation>.
Note: can only be used while the character who's sprite you want to change is talking.

Command: setbackground
Usage: setbackground "<imagelocation>"
Sets the background immage to the image found at <imagelocation>.

Command: action
Usage: action "<dialogue>"
Shows <dialogue> as an action, with no character saying it.

Command: empty
Usage: empty
Removes all character sprites from the screen.

Command: end
Usage: end "<imagelocation>"
Ends the visual novel by displaying the image found at <imagelocation>.

STORY BRANCHES

This is the more experimental version of the program. Branches are included in this version, allowing branching stories.

Command: branch
Usage: branch "<dialogue>" "<opt1>" "<opt2>" "<id1>" "<id2>"
Creates a branch. <dialogue> is the dialogue shown for the branch, <opt1> is the text on button 1 (left side), <opt2> is the text on button 2 (right side),
<id1> is the id of button 1's branch and <id2> is the id of button 2's branch.

Command: <id>
Usage: <id>
Acts as a jumping point for a branch. If a branch was previously created with <id1> being a and <id2> being b, the usage of this command would simply be
a
which would then cause the program to jump to that spot and carry on from the next command.

Warning: branches are untested and prone to breaking.