package com.sparkfits

import nom.tam.fits.{Fits, HeaderCard, Header}
import nom.tam.util.{Cursor}

/**
  * Object to manipulate metadata of the fits file.
  */
object SparkFitsUtil {

  /**
    * Get the number of HDUs in the fits file.
    * This method should be used recursively.
    *
    * @param f : (nom.tam.fits.Fits)
    *   The fits file opened with nom.tam.fits.Fits
    * @param n : (Int)
    *   The index of the current HDU.
    * @return the number of HDU in the fits file.
    *
    */
  def getNHdus(f : Fits, n : Int = 0) : Int = {
    if (f.getHDU(n) != null) getNHdus(f, n + 1) else n
  }

  /**
    * Get the header of a HDU recursively. The method returns a String with all
    * the header. The different elements of the original header are separated by
    * a coma. To access them individually, use the split(",") method on the final
    * string.
    *
    * @param c : (nom.tam.util.Cursor)
    *   Cursor to navigate in the header of the HDU. Work as an Iterator.
    * @param s : (String)
    *   The string that will contain the Header.
    * @return A string containing the Header, whose elements are coma-separated.
    *
    */
  def getMyHeader(c : Cursor[String, HeaderCard], s : String) : String = {
    if (c.hasNext() == true) getMyHeader(c, s + c.next() + ",") else s
  }
}
