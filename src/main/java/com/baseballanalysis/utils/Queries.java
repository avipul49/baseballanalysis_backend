package com.baseballanalysis.utils;

public class Queries {
	public static String teamPerformance = "select yearid,teamid,name,G,W,L,lgid from teams where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%')) order by yearid";
}
