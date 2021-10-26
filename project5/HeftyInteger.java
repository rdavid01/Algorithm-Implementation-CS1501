/**
 * HeftyInteger for CS1501 Project 5
 * @author	Dr. Farnan
 */

import java.util.Random;

public class HeftyInteger {

	private final byte[] ONE = {(byte) 1};
	private final byte[] ZERO = {(byte) 0}; //constant I created to represent a value of 0
	private final byte[] TWO = {(byte) 2}; //constant I created to represent a value of 2

	private byte[] val;

	/**
	 * Construct the HeftyInteger from a given byte array
	 * @param b the byte array that this HeftyInteger should represent
	 */
	public HeftyInteger(byte[] b) {
		val = b;
	}

	/**
	 * Return this HeftyInteger's val
	 * @return val
	 */
	public byte[] getVal() {
		return val;
	}

	/**
	 * Return the number of bytes in val
	 * @return length of the val byte array
	 */
	public int length() {
		return val.length;
	}

	/**
	 * Add a new byte as the most significant in this
	 * @param extension the byte to place as most significant
	 */
	public void extend(byte extension) {
		byte[] newv = new byte[val.length + 1];
		newv[0] = extension;
		for (int i = 0; i < val.length; i++) {
			newv[i + 1] = val[i];
		}
		val = newv;
	}

	/**
	 * If this is negative, most significant bit will be 1 meaning most
	 * significant byte will be a negative signed number
	 * @return true if this is negative, false if positive
	 */
	public boolean isNegative() {
		return (val[0] < 0);
	}

	/**
	 * Computes the sum of this and other
	 * @param other the other HeftyInteger to sum with this
	 */
	public HeftyInteger add(HeftyInteger other) {
		byte[] a, b;
		// If operands are of different sizes, put larger first ...
		if (val.length < other.length()) {
			a = other.getVal();
			b = val;
		}
		else {
			a = val;
			b = other.getVal();
		}

		// ... and normalize size for convenience
		if (b.length < a.length) {
			int diff = a.length - b.length;

			byte pad = (byte) 0;
			if (b[0] < 0) {
				pad = (byte) 0xFF;
			}

			byte[] newb = new byte[a.length];
			for (int i = 0; i < diff; i++) {
				newb[i] = pad;
			}

			for (int i = 0; i < b.length; i++) {
				newb[i + diff] = b[i];
			}

			b = newb;
		}

		// Actually compute the add
		int carry = 0;
		byte[] res = new byte[a.length];
		for (int i = a.length - 1; i >= 0; i--) {
			// Be sure to bitmask so that cast of negative bytes does not
			//  introduce spurious 1 bits into result of cast
			carry = ((int) a[i] & 0xFF) + ((int) b[i] & 0xFF) + carry;

			// Assign to next byte
			res[i] = (byte) (carry & 0xFF);

			// Carry remainder over to next byte (always want to shift in 0s)
			carry = carry >>> 8;
		}

		HeftyInteger res_li = new HeftyInteger(res);

		// If both operands are positive, magnitude could increase as a result
		//  of addition
		if (!this.isNegative() && !other.isNegative()) {
			// If we have either a leftover carry value or we used the last
			//  bit in the most significant byte, we need to extend the result
			if (res_li.isNegative()) {
				res_li.extend((byte) carry);
			}
		}
		// Magnitude could also increase if both operands are negative
		else if (this.isNegative() && other.isNegative()) {
			if (!res_li.isNegative()) {
				res_li.extend((byte) 0xFF);
			}
		}

		// Note that result will always be the same size as biggest input
		//  (e.g., -127 + 128 will use 2 bytes to store the result value 1)
		return res_li;
	}

	/**
	 * Negate val using two's complement representation
	 * @return negation of this
	 */
	public HeftyInteger negate() {
		byte[] neg = new byte[val.length];
		int offset = 0;

		// Check to ensure we can represent negation in same length
		//  (e.g., -128 can be represented in 8 bits using two's
		//  complement, +128 requires 9)
		if (val[0] == (byte) 0x80) { // 0x80 is 10000000
			boolean needs_ex = true;
			for (int i = 1; i < val.length; i++) {
				if (val[i] != (byte) 0) {
					needs_ex = false;
					break;
				}
			}
			// if first byte is 0x80 and all others are 0, must extend
			if (needs_ex) {
				neg = new byte[val.length + 1];
				neg[0] = (byte) 0;
				offset = 1;
			}
		}

		// flip all bits
		for (int i  = 0; i < val.length; i++) {
			neg[i + offset] = (byte) ~val[i];
		}

		HeftyInteger neg_li = new HeftyInteger(neg);

		// add 1 to complete two's complement negation
		return neg_li.add(new HeftyInteger(ONE));
	}

	/**
	 * Implement subtraction as simply negation and addition
	 * @param other HeftyInteger to subtract from this
	 * @return difference of this and other
	 */
	public HeftyInteger subtract(HeftyInteger other) {
		return this.add(other.negate());
	}

	//========================================================================================================================================================================

	/**
	 * Compute the product of this and other
	 * @param other HeftyInteger to multiply by this
	 * @return product of this and other
	 */
	public HeftyInteger multiply(HeftyInteger other) {
		return gradeschool(this, other);
	}

	//---------------------------------------------------------------------------------------------------

	//leftShiftGradeschool is a function that will shift a byte aray to the left by a desired amount
	private HeftyInteger leftShiftGradeschool(byte[] original, int amountToShift){
		int counter = 0; //keeps track of which index we are inserting into new array
		byte saRemainder = (byte) (amountToShift%8); //the remainder of amount to shift divided by 8 (called shift amount remainder) (how much of the byte moves up an index)
		byte saInverse = (byte) (8 - saRemainder); //the difference between 8 and the shift amount (how much of the byte is left in the same index)
													//NOTE: a better name for saInverse would have been saDifference

		//if the first index of the original byte array is not 0, then we need to add a new index on the front in order to store any bits that might be shifted into a new index
			//NOTE: my first draft of leftShiftGradeschool was just the contents within this if, but this caused a problem where everytime it was called, a new byte of 0x00 was added onto the front
					//  this quickly became unmanagable, especially for large numbers that required a lot of shifting
				//PROBLEM SOLVED:  I put inside an if, so that a new index was only created if the first byte could be shifted far enough that it would need a new index
									//if the first byte is 0x00, then it is handled by a case below the if that skips the first step of the upper case (which moves upper bits into a new index)
									//this solution prevetns bytes from being exteded when it is unneccessary and cuts down on excessive padding
		//first byte is not 0x00
		if(original[0] !=0){
			//make new array whose length = (the original length) + (the number of bytes need to shift by)
			byte[] shiftedArr = new byte[original.length + ((amountToShift+saInverse)/8)];  //#bits/8 = #bytes

			//make the new greatest byte taking into account the amount that needs to be shifted

			//PROBLEM: see description below
			shiftedArr[0] = (byte) ((original[0]>>>saInverse)&(~(-1<<(saRemainder)))); //shift the original greatest order byte to the right by saRemainder, extending with 0
													   									//this ensures that a number of bits from the previous byte, equal to amountToShift, is represented in the new byte of the same place value
			counter++; //increment the counter to represent we will be working on the next index of shiftedArr

			//shift the remianing bytes from the orignal factor
			//PROBLEM: see description below 
			for(int i=1; i < original.length; i++){
				shiftedArr[i] = (byte) (((original[i]>>>saInverse)&(~(-1<<(saRemainder))))|(original[i-1]<<saRemainder)); //see description below
				counter++; //increment the counter to represent we will be working on the next index of shiftedArr
			}

			//shift the bits of the last original byte 
			shiftedArr[counter] = (byte) (original[original.length-1]<<saRemainder);
			counter++;

			//fill in the rest of shiftedArr with 0's if neccessary
			for(int i=counter; i < shiftedArr.length; i++){
				shiftedArr[i] = 0;
			}

			//return a new HeftyInteger that contains the shifted byte array
			HeftyInteger shiftedInt = new HeftyInteger(shiftedArr);
			return shiftedInt;
		}

		//first byte is 0x00
		//make new array whose length = (the original length) + (the number of bytes need to shift by -1)
			//subtracting by one because dont want to include excessive padding with 0x00 on the front
		byte[] shiftedArr = new byte[(original.length + ((amountToShift+saInverse)/8))-1]; //#bits/8 = #bytes

		for(int i=1 ; i < original.length; i++){
				//this is the description be refered to above
				//NOTE: PROBLEM: shifting in java can only be done with ints or long, so when I was trying to shift only a bit, java converted a byte to an int produced inaccurate results
						//SOLUTION: created a mask that would isolate the lower 8 bits, and then cast the result of all operations to a byte
				shiftedArr[i-1] = (byte) (((original[i]>>>saInverse)&(~(-1<<(saRemainder)))) | (original[i-1]<<saRemainder)); //for the middle bits, do a bitwise OR comparison so that the new byte will be a shifted version of the original two adjacent bytes
																																//In the new byte,  the first (# of bits = saRemainder)
																															//new byte has the first (# of bits == saInverse), gotten from shifting the bits of the higher order byte (original[i-1]) to the left by an amount of saRemainder
																																//so these bits are the last (# of bits == saInverse) from original[i-1]
																															//new byte has the last (# of bits == saRemainder), gotten from shifing the bits of the lower order byte (original[i]) to the the right by an amount of saInverse
																						  										//so these bits are the first (# of bits == saRemainder) from origianl[i]

																															//the mask is gotten by shifting -1 (all 1 bits in two's complement) left by an amount of saRemainder (the ame length we shifted the higher order bit), and then taking the complement
																																//a bitwise AND is then used so that we mask the lower byte of the shifted value (and get rid of the extra bits that were added when java converted to an int) 

																															//finally, the result is cast to a byte for consistency (we only want the lowest 8 bits of this result to put in the new byte array)
				counter++; //increment the counter to represent we will be working on the next index of shiftedArr
		}
		//shift the bits of the last original byte 
		shiftedArr[counter] = (byte) (original[original.length-1]<<saRemainder);
		counter++;

		//fill in the rest of shiftedArr with 0's if neccessary
		for(int i=counter; i < shiftedArr.length; i++){
			shiftedArr[i] = 0;
		}

		HeftyInteger shiftedInt = new HeftyInteger(shiftedArr);
		return shiftedInt;
	}

	//------------------------------------------------------------------------------------------------------------------------------

	private HeftyInteger gradeschool(HeftyInteger x, HeftyInteger y){		
		byte[] factor1;
		byte[] factor2;
		HeftyInteger largerOperand;

		//figure out which integer is larger (the larger integer is factor1 when multiplying)
			//this is not a necessary step, just one that could slightly improve optimization 
			//that is why I am only comparing length rather than doing an entire call to compareToForXGCD
		//set all factors to positive before multiplying, this is necessary to make the bit shifting operations simpler
		//x is greater than y in length
		if(x.length() > y.length()){
			largerOperand = x;
			//if either of the operands are negative, store the positive version of that number in the factor variable
				//we can do this because multiplication will produce a result that has the same numbers in the same place values
				//we just have to make sure if only one of the values is negative, then we negate the final answer (so that the bits will be represented in their negative 2's complement form)
			if(x.isNegative()){
				factor1 = x.negate().getVal();  //negate the negative value to get the positive value
			}
			else{
				factor1 = x.getVal();  //the HeftyInteger provided is already positive
			}
			if(y.isNegative()){
				factor2 = y.negate().getVal(); //negate the negative value to get the positive value (for bit-shifting multiplication)
			}
			else{
				factor2 = y.getVal(); //the HeftyInteger provided is already positive
			}
		}
		//y is greater than x in length
		else{
			largerOperand = y;
			if(y.isNegative()){
				factor1 = y.negate().getVal();
			}
			else{
				factor1 = y.getVal();
			}
			if(x.isNegative()){
				factor2 = x.negate().getVal();
			}
			else{
				factor2 = x.getVal();
			}
		}


		int amountShiftBy = 0; //initialize the variable amountShiftBy
		//make a HeftyInteger whose value is zero
			//NOTE: I made this before I decided to create a global variable for the ZERO byte array, so that the only reason why I didn't use the constant ZERO to make this HeftyInteger
		byte[] zeroArr = new byte[1]; 
		HeftyInteger product = new HeftyInteger(zeroArr);

		//for loop to go thorugh each byte in the lower length factor (tells when we should add to the product (1 add, 0 nothing))
		for(int i=factor2.length-1; i >=0; i--){ 
			byte currByte = factor2[i];
			for(int bitNum = 0; bitNum < 8; bitNum++){ //for loop to go through each bit of each byte
				//bitNum is the bit we want to be looking at
				int bitSetting = ((factor2[i]>>bitNum)&1); //gives the value of the it we want to be looking at
				//only want to do a shift if the bit in the current place value is 1 (dont multiply when 0 because just get 0)
				if( bitSetting == 1){//add the larger length factor to the product, accounting for appropriate shifts
					HeftyInteger component = leftShiftGradeschool(factor1, amountShiftBy);
					
					product = product.add(component); //add the result to the running total for the product
				
				}

				amountShiftBy++; //the amount we shift by gets increased by one for each place value we go up (regardless of if it is a 1 or 0)
			}
		}
		
		//return a negated product if only one of the factors is negative
		if( (!x.isNegative() && y.isNegative()) || (x.isNegative() && !y.isNegative()) ){
			HeftyInteger negativeProduct = product.negate();
			return negativeProduct;
		}
		//else return the positive product
		return product;
	}




	//========================================================================================================================================================================
	

	//compare two HeftyIntegers to see which one is larger
		//ignores padding to make this calculation
	public int compareToForXGCD(HeftyInteger other){
		//since this compareTo will only be used in my exteded euclidean algorithm, it only needs to consider positive values

		//return 1 means (this > other)
		//return -1 means (this < other)
		//return 0 means (this == other)

		//get the byte arrays of this and other
		byte[] thisArr = this.getVal();
		byte[] otherArr = other.getVal();

		//after running the first for loop below, these ints will hold the length of each array - padding amount (getting the length of each heftyint wihtout padding)
		int thisLengthNoPad = this.length();
		int otherLengthNoPad = other.length();
		//after running the first for loop below, these ints will hold the starting position of each array based on the first non-zero (non-padding) byte
			//starting points (index of most significant byte) wihtout padding
		int thisStartPoint = 0;
		int otherStartPoint = 0;
		//booleans to represent if we have found all the padding in an array
		boolean thisPadFound = false;
		boolean otherPadFound = false;
		//find which array is longer with padding factored in (needed for when we are looping)
		int largerLength; //this variable will store the larger length array that includes padding
		if(this.length() > other.length()){
			largerLength = this.length();
		}
		else{
			largerLength = other.length();
		}
		//move thorugh each array, subtracting one off of this/otherLengthNoPad each time a 0 byte is found
		//will stop subtracting from this length whenever reach non-padding bytes
		for(int i=0; i < largerLength; i++){
			//update lengthNopad and startPoint as long as:
				// we haven't found all the padding 
				// we are within the bounds of the array
				// the current byte is 0x00
			if(!thisPadFound && (i < thisArr.length) && thisArr[i] == 0){
				thisLengthNoPad--;
				thisStartPoint++;
			}
			//else if we havent already found all the padding, mark that we have found all the padding (if one of the conditions is not met)
			else if(!thisPadFound){
				thisPadFound = true;
			}
			if(!otherPadFound && (i < otherArr.length) && otherArr[i] == 0){
				otherLengthNoPad--;
				otherStartPoint++;
			}
			else if(!otherPadFound){
				otherPadFound = true;
			}
		}

		//if the two arrays are differing lengths, we return
		if(thisLengthNoPad > otherLengthNoPad){
			return 1;
		}
		if(thisLengthNoPad < otherLengthNoPad){
			return -1;
		}
		//else they are both the same length when padding is exluded
		//same lengths, do byte by byte comparrison (for length of non-padded portions of array)
		for(int i=0; i < thisLengthNoPad; i++){
			//check if the byte values differ
			if(thisArr[thisStartPoint+i] != otherArr[otherStartPoint+i]){
				//NOTE: this is a much simpler way of masking than I implemented in leftShiftGradeschool
					//it may be different since I am not shifting ints here (which would risk bringing in leading 1's that are used for sign extension)
					//but it may be worth while to go back and see if this version of masking could be implemented in leftShiftGradeschool
				int thisMask = thisArr[thisStartPoint+i] & 0xff;
				int otherMask = otherArr[otherStartPoint+i] & 0xff;

				//if thisArr's value is > otherArr's value  (this > other)
				if(thisMask > otherMask){
					return 1;
				}
				//else otherArr's value is > thisArr's value (this < other)
				return -1;
			}
		}

		//if made it through loop without returning, then the HeftyIntegers must be equal
		return 0;
	}

	//------------------------------------------------------------------------------------------------------------------

	/**
	 * Run the extended Euclidean algorithm on this and other
	 * @param other another HeftyInteger
	 * @return an array structured as follows:
	 *   0:  the GCD of this and other
	 *   1:  a valid x value
	 *   2:  a valid y value
	 * such that this * x + other * y == GCD in index 0
	 */

	 public HeftyInteger[] XGCD(HeftyInteger other) {
	 	//HeftyIntegers to store the absolute values of this and other 
	 		//(I am performing extendEuclid by using only positive #'s and then changing the sign of the coefficients at the end if neccessary)
	 	HeftyInteger thisPos;
	 	HeftyInteger otherPos;

	 	//make sure that both values are positive before finding gcd (only changes the return values)
	 	if(this.isNegative()){
	 		thisPos = this.negate();
	 	}
	 	else{
	 		thisPos = this;
	 	}
	 	if(other.isNegative()){
	 		otherPos = other.negate();
	 	}
	 	else{
	 		otherPos = other;
	 	}

	 	HeftyInteger zeroInt = new HeftyInteger(ZERO);
	 	HeftyInteger oneInt = new HeftyInteger(ONE);

	 	//perform a check to see which is greater, this or other 
	 	int comparison = thisPos.compareToForXGCD(otherPos);

	 	//whichever HeftyInteger is greater (this or other) should be passed into extendEuclid as the first argument
	 	//based on the comparison, this will tell us how to return the coeficients 
	 		//(depends on wheter this was first argument, or we switched the order and made other the first argument)

	 	//|this| == |other|
	 	if(comparison == 0){
	 		//return an array showing that the gcd is itself
	 		//if other is negative, then need to flip sign and return -1 in the index for coeficient of x, otherwise skip this conditional and 1 is used as the coefX
	 		if(other.isNegative()){
	 			oneInt = oneInt.negate();
	 		}
	 		//use thisPos as the gcd because want to use the absolute value of this ( |this| )
	 		//0 is the coefX,  1 or -1 is the coefY
	 		HeftyInteger[] retVals = new HeftyInteger[] {thisPos, zeroInt, oneInt};

	 		return retVals;
	 	}

	 	//Since we are always using the larger value as the first argument, we need two different cases to make sure we return the correct x and y coefficients
	 		// because if order submitted is xgcd(this, other),  and then I switch it to be extendEuclid(otherPos, thisPos), will return backwards x and y 
	 			//so just need to switch the contents of  retVals[1] and retVals[2] if  |this| < |other|

		//|this| > |other|
	 	else if(comparison ==1){
	 		//this is a (paired with x),  other is b (paired with y)
	 		HeftyInteger[] retVals = extendEuclid(thisPos, otherPos);//pass in this as the argument for a, because thisPos > otherPos
	 		//flip coefficient sign if we swaped the sign of this/other with the arguments we passed into extendEuclid()
	 		if(this.isNegative()){
	 			retVals[1] = retVals[1].negate();
	 		}
	 		if(other.isNegative()){
	 			retVals[2] = retVals[2].negate();
	 		}
	 		return retVals;
	 	}

	 	//|this| < |other|
	 	//else (comparrison == -1)
	 	//other is a (paired with x), this is b (paired with y)
	 	HeftyInteger[] retVals = extendEuclid(otherPos, thisPos);//pass in other as the argument for a, because otherPos > thisPos
	 	//flip coefficient sign if we swaped the sign of this/other with the arguments we passed into extendEuclid()
	 	if(other.isNegative()){
	 		retVals[1] = retVals[1].negate();
	 	}
		if(this.isNegative()){
	 		retVals[2] = retVals[2].negate();
	 	}
		//need to switch indcies 1 and 2 so that are returing the coefficient of this and other in the correc spot
	 	HeftyInteger temp = retVals[1];
	 	retVals[1] = retVals[2];
	 	retVals[2] = temp;

	 	return retVals;
	 }

	 //-------------------------------------------------------------------

	 //returns an array with gcd, x coeficient, and y coeficient
	 	//vals[0] = gcd
	 	//vals[1] = xCoef
	 	//vals[2] = yCoef
	 private HeftyInteger[] extendEuclid(HeftyInteger a, HeftyInteger b){
	 	HeftyInteger zero = new HeftyInteger(ZERO);
	 	HeftyInteger[] returnArr;

	 	//base case: remainder 0, dont keep recursing
	 	if(b.compareToForXGCD(zero) == 0){
	 		returnArr = new HeftyInteger[] {a, new HeftyInteger(ONE), new HeftyInteger(ZERO)};
	 		return returnArr;
	 	}
	 	
	 	//get division and mod results
	 		//just make one call to a.divide(b), then take the values from the array being reutrned
	 			//divResults[0] = a/b
	 			//divResults[1] = a%b
	 	HeftyInteger[] divResults = a.divide(b);

	 	//recurse
	 	HeftyInteger[] vals = extendEuclid(b, divResults[1]);  //extendEuclid(b, a%b) 

	 	//find values for divisor, x coefficient, and y coefficient
	 	HeftyInteger divisor = vals[0];
	 	HeftyInteger x = vals[2];
	 	HeftyInteger y = vals[1].subtract(divResults[0].multiply(x));  //((a/b).multiply(x));

	 	//return an array of HeftyIntegers with gcd, xCoef, yCoef
	 	returnArr = new HeftyInteger[] {divisor, x, y};
		return returnArr;
	 }


	 public HeftyInteger[] divide(HeftyInteger other){
	 	//this is the dividend
	 	//other is the divisor

	 	HeftyInteger quotient = new HeftyInteger(ONE); 
	 	HeftyInteger two = new HeftyInteger(TWO);

	 	HeftyInteger shifted = new HeftyInteger(other.getVal());
	 	HeftyInteger newShifted = shifted;
	 	HeftyInteger  newQuotient = quotient;
	 	int comparison = this.compareToForXGCD(shifted);

	 	//compare the dividend to the divisor, shift the divisor left by one each time it is less than the dividend
	 		//this simulates dividing by two for each shift
	 	//NOTE: PROBLEM: was going one iteration too far, becuase needed the last (shifted) value that was <= divident (comparison not 1)
	 			//SOLUTION:  have a variables to keep track of the new Shifted/Quotient and old Shifted/Quotient
	 						//when new shifted/quotient does not meet criteria based on comparison,  can perform necessary operations on the previous shifted and quotient
	 						// new -> newShifted and newQuotient       previous -> shifted and quotient
	 	while(comparison == 1){
	 		shifted = newShifted;
	 		newShifted = shifted.leftShiftGradeschool(shifted.getVal(), 1); //shift the divisor left by one each time it is less than the dividend
	 		quotient = newQuotient;
	 		newQuotient = quotient.multiply(two); //the quotient doubles each time we shift (this means we can divide by 2 more than before)
	 		comparison = this.compareToForXGCD(newShifted);  //update comparison to be testing with the newly shifted value

	 	}

	 	//subtract shifted from this to find the remainder we have left to divide (it may still be > the divisor)
	 	HeftyInteger difference = this.subtract(shifted);
	 	int remainderComp = difference.compareToForXGCD(other);

		
	 	//remainer is still > divisor, need to call divide again
	 	if(remainderComp == 1){
	 		HeftyInteger[] recurseDivResult = difference.divide(other);//remainder is still > divisor,  need to divide again
	 		recurseDivResult[0] = quotient.add(recurseDivResult[0]);
	 		return recurseDivResult;	
	 	}
	 	//remainder is < divisor, have a remainder to return (this remainder is the mod of this divided by other)
	 	else if(remainderComp == -1){
	 		//have an actual remainder to return (non-zero and < divisor)
	 		HeftyInteger[] divResult = new HeftyInteger[] {quotient, difference}; //return quotient and a remainder of difference
	 		return divResult;
	 	}
	 	//else  (remainderComp == 0){
 		//remiander is 0
		//but since the difference is exactly the same as the divisor, we also need to add 1 to quotient (because can fit divisor into dividend one more time)
		HeftyInteger one = new HeftyInteger(ONE);
		HeftyInteger zero = new HeftyInteger(ZERO); //making heftyintegers for the values of 1 and 0
	 	quotient = quotient.add(one); //add one to quotient
	 	HeftyInteger[] divResult = new HeftyInteger[] {quotient, zero}; //return quotiend and remainder of 0
		return divResult;

	}


}
