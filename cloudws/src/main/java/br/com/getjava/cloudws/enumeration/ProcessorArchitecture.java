package br.com.getjava.cloudws.enumeration;

public enum ProcessorArchitecture{
	
	bit32("32-bit"),bit64("64-bit"),bit3264("32bit,64bit");
	
	public final String bit;
	
	ProcessorArchitecture(String bit){
		this.bit = bit;
	}
}