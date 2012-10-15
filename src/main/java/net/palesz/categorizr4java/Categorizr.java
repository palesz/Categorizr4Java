package net.palesz.categorizr4java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static net.palesz.categorizr4java.Category.*;

public class Categorizr {

	private final IClientInfo clientInfo;
	private final ICategoryHolder categoryHolder;
	private final Options options;

	public Categorizr(IClientInfo clientInfo,
					  ICategoryHolder categoryHolder) {
		this(clientInfo, categoryHolder, new Options());
	}

	public Categorizr(IClientInfo clientInfo,
					  ICategoryHolder categoryHolder,
					  Options options) {
		assert clientInfo != null;
		assert categoryHolder != null;
		assert options != null;

		this.clientInfo = clientInfo;
		this.categoryHolder = categoryHolder;
		this.options = options;
	}

	public Category categorize() {
		Category category = clientInfo.preference();

		if (category != null) {
			categoryHolder.set(category);
		}

		if (categoryHolder.get() == null) {
			String userAgent = clientInfo.userAgent();

			if (match("GoogleTV|SmartTV|Internet.TV|NetCast|NETTV|AppleTV|boxee|Kylo|Roku|DLNADOC|CE-HTML", userAgent)) {
				category = TV;
			} else if (match("Xbox|PLAYSTATION.3|Wii", userAgent)) {
				category = TV;
			} else if (match("iP(a|ro)d", userAgent)) {
				category = TABLET;
			} else if (match("tablet", userAgent) && !match("RX-34", userAgent)) {
				category = TABLET;
			} else if (match("FOLIO", userAgent)) {
				category = TABLET;
			} else if (match("Linux", userAgent) && match("Android", userAgent) && !match("Fennec|mobi|HTC.Magic|HTCX06HT|Nexus.One|SC-02B|fone\\.945", userAgent)) {
				category = TABLET;
			} else if (match("Kindle", userAgent) || match("Mac.OS", userAgent) || match("Silk", userAgent)) {
				category = TABLET;
			} else if (match("GT-P10|SC-01C|SHW-M180S|SGH-T849|SCH-I800|SHW-M180L|SPH-P100|SGH-I987|zt180|HTC(.Flyer|_Flyer)|Sprint.ATP51|ViewPad7|pandigital(sprnova|nova)|Ideos.S7|Dell.Streak.7|Advent.Vega|A101IT|A70BHT|MID7015|Next2|nook", userAgent)) {
				category = TABLET;
			} else if (match("MB511", userAgent) && match("RUTEM", userAgent)) {
				category = TABLET;
			} else if (match("BOLT|Fennec|Iris|Maemo|Minimo|Mobi|mowser|NetFront|Novarra|Prism|RX-34|Skyfire|Tear|XV6875|XV6975|Google.Wireless.Transcoder", userAgent)) {
				category = MOBILE;
			} else if (match("Opera", userAgent) && match("Windows.NT.5", userAgent) && match("HTC|Xda|Mini|Vario|SAMSUNG-GT-i8000|SAMSUNG-SGH-i9", userAgent)) {
				category = MOBILE;
			} else if ((match("Windows.(NT|XP|ME|9)", userAgent) || match("Win(9|.9|NT)", userAgent)) && !match("Phone", userAgent)) {
				category = DESKTOP;
			} else if (match("Macintosh|PowerPC", userAgent) && !match("Silk", userAgent)) {
				category = DESKTOP;
			} else if (match("Linux", userAgent) && match("X11", userAgent)) {
				category = DESKTOP;
			} else if (match("Solaris|SunOS|BSD", userAgent)) {
				category = DESKTOP;
			} else if (match("Bot|Crawler|Spider|Yahoo|ia_archiver|Covario-IDS|findlinks|DataparkSearch|larbin|Mediapartners-Google|NG-Search|Snappy|Teoma|Jeeves|TinEye", userAgent) && !match("Mobile", userAgent)) {
				category = DESKTOP;
			} else {
				category = MOBILE;
			}

			if (options.isCategorizeTabletsAsDesktop() && TABLET.equals(category)) {
				category = DESKTOP;
			}

			if (options.isCategorizeTVsAsDesktop() && TV.equals(category)) {
				category = TV;
			}

			categoryHolder.set(category);
		}

		return category;
	}

	boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

}
