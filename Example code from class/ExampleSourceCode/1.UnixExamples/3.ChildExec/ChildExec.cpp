//*******************************************************************************
//**  File: ChildExec.cpp
//**  Date: October 2006
//**  Author: Kelvin Sung
//******************************************************************************
#include <sys/types.h>   // for fork, wait
#include <sys/wait.h>    // for wait
#include <unistd.h>      // for fork, pipe, dup, close
#include <stdio.h>       // for NULL, perror
#include <iostream>      // for cout
using namespace std;

int main( int argc, char** argv ) {
      int fds[2];
      int pid;

      // create a pipe
      // fds[0] - is input for the pipe (we want to input from here)
      // fds[1] - is the output channel for the pipe (we want to output to here)
      if ( pipe( fds ) < 0 ) { perror( "pipe error" ); exit(1); }

      // fork a child
      if ( ( pid = fork( ) ) < 0 ) { perror( "fork error" ); exit(1); }

      if ( pid == 0 ) {
          // I'm a child
          // I wake up knowing my parent's stdin(0), stdout(1), and stderr(2)
          // I want to listen to my parent from the pipe, instead of from stdin
          dup2(fds[0], 0); // we want pipe[0] to be the same as stdin(0)
          close(fds[1]);     // we don't want to do anything to pipe[1] (parent will)

          char c[100];
          cin >> c;
          cout << "Child got: [" << c << "], making itself into a: " << c << "\n";
          execlp(c, c, NULL, (char *) 0);

          // **** this part of the child process will _NEVER_ be reach! ***
          cerr << "This will NEVER appear, really!!";
      } else {
          // I'm a parent
          close(fds[0]);
          dup2(fds[1], 2);    // stderr(2) will now go into pipe[1]
      
          int c=0;
          char cmd[100];

          cout << "Kelvin_Shell[" << c << "]: ";
          cin >> cmd;
          cerr << cmd << "\n";    // send to child
      
          // child should not be there anymore!
          wait( NULL );
          cout << "all done playing, fun?" << endl;
      }
}
