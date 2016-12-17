package net.stenuit.xavier.tools;

import java.util.ArrayList;
import java.util.List;

public class Converter {
	public static String bin2hex(byte[] bin)
	{
		final char[] nibble={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		String ret="";
		for(byte in:bin)
		{
			ret+=nibble[(in&0x00F0)>>4];
			ret+=nibble[(in&0x000F)];
		}		
		return ret;
	}
	public static byte[] hex2bin(String hex)
	{
		hex=hex.toUpperCase();
		boolean isNibble1=true;
		List<Byte> ret=new ArrayList<Byte>();
		byte curbyte=(byte)0;
		int prout;
		
		for(int i=0;i<hex.length();i++)
		{
			char c=hex.charAt(i);
			if(c<'A')
			{
				prout=(int)c-(int)'0';
			}
			else
			{
				prout=(int)c-(int)'A'+10;
			}
			if((prout<0)||(prout>15)) throw new IllegalArgumentException("Illegal character found : "+c);
			
			if(isNibble1)
			{
				curbyte=(byte)(prout<<4);
			}
			else
			{
				curbyte|=prout;
				ret.add(curbyte);
				curbyte=(byte)0;
			}
			isNibble1=!isNibble1;
		}
		
		byte[] rret=new byte[ret.size()];
		int idx=0;
		for(Byte bibi:ret)
		{
			rret[idx++]=bibi.byteValue();
		}
		return rret;
	}
	public static void main(String[] args)
	{
		byte[] input=new byte[]{(byte)0xAB,(byte)0xCD,(byte)0x12,(byte)0x34,(byte)0xFF,(byte)0x00};
		System.out.println(bin2hex(input));
		String toto="AABBCAFEBABE1234567890";
		System.out.println(bin2hex(hex2bin(toto)));
	}
}
