package com.baseballanalysis.utils;

public class Queries {
	public static String teamPerformance = "select yearid,lgid,teamid,name,G,W,L,R,AB,H,HR,SO,CS,RA,ER,CG,SHO,HA,HRA,FP,attendance,SOA,RANK from teams where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%')) order by yearid";
	public static String playerSalary = "select playerid,sum(salary) from \"Salaries\" where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%')) group by playerid";
	public static String teamOrientationBattingPitching = "select batting.teamid,battingRank,pitchingRank,runs,outs from (select runs,battingRank, teamid from (select runs,rownum as battingRank, teamid from (select sum(t.R) as runs, t.teamid from teams t where t.yearid>=%s and t.yearid<=%s group by t.teamid order by runs desc)) where"
			+ "('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) batting,"
			+ "(select outs,pitchingRank, teamid from (select outs,rownum as pitchingRank, teamid from (select sum(t.SOA) as outs, t.teamid from teams t where t.yearid>=%s and t.yearid<=%s group by t.teamid order by outs desc)) where"
			+ "('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) pitching where batting.teamid = pitching.teamid";

	public static String teamTendency = "select playerid, CONCAT(namefirst,CONCAT(' ',namelast)), bats, throws from master where playerid in"
			+ "(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%')))";

	public static String playerBirthCountry = "select birthcountry, count(playerid) from master where playerid in(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) group by BIRTHCOUNTRY";
	public static String playerBirthState = "select CONCAT('US-',birthstate), count(playerid) from master where playerid in(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) group by CONCAT('US-',birthstate)";
	public static String playerBirthCity = "select birthcity, count(playerid) from master where playerid in(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) group by birthcity";
	public static String allManagers = "select distinct managers.playerid, CONCAT(namefirst,CONCAT(' ',namelast)) from managers ,master where managers.playerid = master.playerid;";
	public static String searchPlayer = "select playerid, CONCAT(namefirst,CONCAT(' ',namelast)) as name from master where playerid in (select distinct playerid from appearances where yearid>=%s and yearid<=%s) and (LOWER(namefirst) like LOWER('%%%s%%') or LOWER(namelast) like LOWER('%%%s%%')) and ROWNUM < 100";

}
