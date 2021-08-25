// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

// boolean isKeyBoardDown;
// var currentPosition;
// while (true) {
//    if (isKeyBoardDown) {
//      if (currentPosition < end) {
//          M[currentPosition++] = 1;
//       }
//    } else {
//          M[currentPosition--] = 1;
//    }
//  }

@SCREEN
D=A
@position
M=D
(start)
    @KBD
    D=M
    @black
    D;JNE
    @white
    D;JEQ
(black)
    @position
    D=M
    @24575
    D=D-A
    @start
    D;JGT
    @position
    M=M+1
    A=M-1
    M=0
    M=!M
    @start
    0;JMP
(white)
    @position
    D=M
    @SCREEN
    D=D-A
    @start
    D;JLT
    @position
    M=M-1
    A=M+1
    M=0
    @start
    0;JMP