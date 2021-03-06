package com.gt22.gt22core.utils;

import java.util.List;
import net.minecraft.util.MathHelper;

/**
 * Some functions that i didn'd fount in {@link Math} or {@link MathHelper}
 * @author gt22
 */
public class Gt22MathHelper
{
	/**
	 * Return difference between a and b;
	 */
	public static int diff(int a, int b)
	{
		return Math.abs(a - b);
	}
	/**
	 * swaps a and b, java doesn't allow changin int value passed into fucnction so usage is b = swap(a, a=b);
	 * @return the a value, before b value assigned to it
	 */
	public static int swap(int a, int b)
	{
		return a;
	}
	
	/**
	 	Can be used to get position of closest int in list to passed value
	 */
	public static int getPositionOfClosest(List<Integer> searchin, int value)
	{
		if(searchin.size() == 0)
		{
			return -1;
		}
		int closestdiff = diff(0, value);
		int closestpos = 0;
		for(int pos = 0; pos < searchin.size(); pos++)
		{
			int i = searchin.get(pos);
			int diff = diff(i, value);
			if(diff < closestdiff)
			{
				closestdiff = diff;
				closestpos = pos;
			}
		}
		return closestpos;
	}
}
