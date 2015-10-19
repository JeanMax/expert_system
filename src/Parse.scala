// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   Parse.scala                                        :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/24 08:51:41 by mcanal            #+#    #+#             //
//   Updated: 2015/10/19 14:09:18 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

//package expert_system

import scala.io.Source //reading
import scala.util.matching.Regex //parsing

object Parse
{
	var lines: Array[String] = null //we'll store rules too... TODO: rename?
	var query: String = null
	var facts: String = null

	def error(s: String) = 
	{
		println(s)
		System.exit(1)
	}

	def firstStep(filename: String) =
	{
		try
		{
			lines = io.Source.fromFile(filename).getLines.toArray //read...
		}
		catch
		{
			case ex: Exception => error("Opening file failed...")
		}

		val wrongChar = "[^A-Z=><+!?|()^]+".r
		for (i <- 0 to (lines.length - 1))
		{
			lines(i) = lines(i).replaceAll("\\s", "") //remove spaces
			lines(i) = lines(i).replaceAll("#.*$", "") //remove comments

			if (wrongChar.findFirstIn(lines(i)) != None)
				error("Weird char found on line: " + lines(i))

			if (lines(i).indexOf('?', 0) == 0)
			{
				if (query == null)
					query = lines(i).substring(1) //parse query
				else
					error("multi-lines queries? seriouslee?! nah...")
				lines(i) = ""
			}

			if (lines(i).indexOf('=', 0) == 0)
			{
				if (facts == null)
					facts = lines(i).substring(1) //parse facts
				else
					error("multi-lines facts? seriouslee?! nah...")
				lines(i) = ""
			}
		}
		lines = lines.filter(_ != "") //remove empty lines

		if (lines == null || query == null || facts == null)
			error("Hmm... Something went wrong.")
	}
}
/*
object Main
{
	def main(args: Array[String]) : Unit =
	{
		if (args.isEmpty || args.length > 1)
			Parse.error("Gimme a file boy...")

		Parse.firstStep(args(0))

		println("-facts:\n" + Parse.facts) //debug
		println("\n-rules:") //debug
		Parse.lines.foreach(println) //debug
		println("\n-query:\n" + Parse.query) //debug
	}

//	Main.main(args)
}
 */
