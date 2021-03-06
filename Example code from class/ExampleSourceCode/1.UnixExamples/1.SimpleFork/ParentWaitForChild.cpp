//******************************************************************************
//**  File: ParentWaitForChild.cpp
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
      int pid;

      // fork a child
      if ( ( pid = fork( ) ) < 0 ) {
          perror( "fork error" );
          exit(1);
      } 
  
      if ( pid == 0 ) {
          // I'm a child
          for (int i=0; i<10; i++) {
              sleep(2);
              cerr << "From Child i=" << i << "\n";
          }
          cerr << "Child going to exit now\n";
          exit( 0 );
      } else {
          // I'm a parent
          for (int j=0; j<10; j++) {
              sleep(1);
              cerr << "From Parent j=" << j << "\n";
          }
          cerr << "Parent going to wait now ...\n";
          wait( NULL );
          cout << "commands completed" << endl;
      }
}
