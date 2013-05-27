package com.gaastats.util

import java.text.DecimalFormat

object Format {
	def formatInteger(int: Int) = {
        new DecimalFormat("00").format(int)
    }
}