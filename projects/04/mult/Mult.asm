// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// Put your code here.

@i
M=1             // i = 0
@result
M=0            // result = 0
(LOOP)
@i
D=M             // D = i
@R1
D=D-M           // D = i - M[R1]
@END            // A = &END
D;JGT
@R0
D=M             // D = M[R0]
@result
M=D+M           // result += M[R0]
@i
M=M+1           // i++
@LOOP
0;JMP
(END)
@result
D=M
@R2
M=D