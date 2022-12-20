# Chess
*2 Player Game*
---
**INTRODUCTION**

This was meant to be a fun coding project. I enjoy playing chess, so I thought it would be fun to try and create it. I have trie to code all of the mechanics.  If I have missed anything, please post an issue. 
---
**RULES**
*[FIDE Laws of Chess](https://www.fide.com/FIDE/handbook/LawsOfChess.pdf)*

Chess is a two-player strategy game played on a checkered gameboard. The gameboard consists of 64 squares arranged in an 8x8 grid. Each player begins the game with 16 pieces: one king, one queen, two rooks, two knights, two bishops, and eight pawns. The goal of the game is to checkmate the opponent's king, which means the king is in a position to be captured (in "check") and there is no way to move the king out of capture (mate).

Each piece has its own unique movements. The king can move one square in any direction. The queen can move any number of squares along a rank, file, or diagonal. The rook can move any number of squares along a rank or file. The bishop can move any number of squares diagonally. The knight moves to any of the squares immediately adjacent to it, then makes a separate, 90-degree turn and moves to an adjacent square on that line, basically moving in an "L" shape. The pawn can move forward one square, but captures diagonally.

The game begins with the white player making the first move, and then players alternate turns. A player can "castle" by moving the king two squares towards a rook on the player's first rank, then moving the rook to the square over which the king crossed.

There are also several special moves in chess called "promotions," "en passant," and "pawn promotion." When a pawn advances to the eighth rank, it is promoted to a queen, rook, bishop, or knight of the same color. If an opponent's pawn advances two squares from its starting position, and lands immediately adjacent to an enemy pawn, the enemy pawn has the option of capturing the pawn "en passant" (in passing) as if it had only advanced one square. A pawn can also be promoted to any piece when it advances to the opponent's eighth rank.

The game is won by placing the opponent's king in a position where it cannot escape capture (checkmate). The game can also end in a draw if the players agree to it, or if there are not enough pieces on the board to force a checkmate (called a "stalemate").

---
**ADDITIONAL INFORMATION**

I have added additional mechanics, such as printing out moves using [Algebraic Chess Notation](https://en.wikipedia.org/wiki/Algebraic_notation_(chess)), as well as being able to start at a position given a FEN[^1] string. 
---
**DISCLAIMER**

This is by no means efficiently or well-programmed. This was meant to be a fun coding project. 

[^1]: [Forsth-Edwards Notation](https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation)
