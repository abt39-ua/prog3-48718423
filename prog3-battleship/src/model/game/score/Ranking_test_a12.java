package model.game.score;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Side;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;
import model.game.GameShip;

public class Ranking_test_a12 {

	@Test
	public void test_wins() {
		WinsScore wins1 = new WinsScore(Side.IMPERIAL);
		DestroyedFightersScore destroyed1 = new DestroyedFightersScore(Side.IMPERIAL);
		
		WinsScore wins2 = new WinsScore(Side.REBEL);
		DestroyedFightersScore destroyed2 = new DestroyedFightersScore(Side.IMPERIAL);
		
		WinsScore wins3 = new WinsScore(Side.IMPERIAL);
		DestroyedFightersScore destroyed3 = new DestroyedFightersScore(Side.IMPERIAL);
		
		
		wins1.score(1);
		wins1.score(1);
		wins1.score(1);
		
		wins2.score(1);
		wins2.score(1);
		
		wins3.score(1);
		wins3.score(1);
		
		
		Ranking<WinsScore> rs = new Ranking<>();
		rs.addScore(wins1);
		rs.addScore(wins2);
		rs.addScore(wins3);
		
		System.out.println(rs);
		
		assertEquals("ranking", " | Player IMPERIAL: 3 | Player IMPERIAL: 2 | Player REBEL: 2 | ", rs.toString());
		
	}
	@Test
	public void test_destroyed() {
		GameShip ms = new GameShip("holi", Side.IMPERIAL);
		DestroyedFightersScore dfs1 = new DestroyedFightersScore(Side.IMPERIAL);
		DestroyedFightersScore dfs2 = new DestroyedFightersScore(Side.REBEL);
		DestroyedFightersScore dfs3 = new DestroyedFightersScore(Side.IMPERIAL);
	
		dfs1.score(new TIEFighter(ms));		// 110 85
		dfs1.score(new TIEInterceptor(ms));	// 145 85 = 230
		dfs1.score(new TIEFighter(ms));		// 110 85
		
/*
					attack		velocity		shield		getValue
Fighter				80			100				80			180
--------------------------------------------------------------------
TIEFighter			85			110				70			195			
TIEInterceptor		85			145				60			230
TIEBomber			30			70				115			100
--------------------------------------------------------------------
XWing				100			110				80			210
YWing				70			80				110			150
AWing				85			140				30			225
---------------------------------------------------------------------	
*/
		
		
		dfs2.score(new XWing(ms));	// 110  100
		dfs2.score(new YWing(ms));	// 80 70
		
		dfs3.score(new XWing(ms));
		dfs3.score(new YWing(ms));	
		
		
		Ranking<DestroyedFightersScore> rd = new Ranking<>();
		rd.addScore(dfs1);
		rd.addScore(dfs2);
		rd.addScore(dfs3);
		System.out.println(rd);

		assertEquals(" | Player IMPERIAL: 620 | Player IMPERIAL: 360 | Player REBEL: 360 | ", rd.toString());

	}

}
