package net.caprazzi.tools.sbatti.io;

public interface IDataProbe<TData> {

	public abstract void capture(TData data);

}