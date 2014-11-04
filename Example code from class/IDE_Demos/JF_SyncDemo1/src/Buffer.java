// Fig. 26.11: Buffer.java
// Buffer interface specifies methods called by Producer and Consumer.
public interface Buffer
{
   // place int value into Buffer
   public void set( int value ) throws InterruptedException; 

   // obtain int value from Buffer
   public int get() throws InterruptedException; 
} // end interface Buffer

/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *************************************************************************/
