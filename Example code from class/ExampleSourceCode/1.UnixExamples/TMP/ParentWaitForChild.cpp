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
      fork( );
	  
	  fork();

	  cout << "Print\n";

}
