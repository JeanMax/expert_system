// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   Main.scala                                         :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/10/18 20:50:55 by mcanal            #+#    #+#             //
//   Updated: 2015/10/19 14:27:45 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

//package expert_system

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
}


