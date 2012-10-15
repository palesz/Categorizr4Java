package net.palesz.categorizr4java;

/**
 * Usually this value stored in the session/cookie.
 */
public interface ICategoryHolder {

	Category get();
	void set(Category category);

}
