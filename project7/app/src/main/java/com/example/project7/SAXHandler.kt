package com.example.project7


import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler


class SAXHandler : DefaultHandler() {

    private var ballonArray : ArrayList<Balloon> = ArrayList<Balloon> ()
    private var validText : Boolean = false
    private var item : Balloon? = null
    private var element : String = ""

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)

        if(qName != null){

            element = qName

            validText = true
            if (element.equals( "balloon"))
                item = Balloon(0,0,0)

        }

    }


    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)

        if (ch != null) {
            var text: String = String(ch, start, length)

            if (validText && item != null) {
                if (element.equals("x"))
                    item!!.setX(text.toInt())
                else if (element.equals("y"))
                    item!!.setY(text.toInt())
                else if(element.equals("radius"))
                    item!!.setRadius(text.toInt())
                Log.w("MainActivity", "text: " + text)
            }
        }
    }
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        validText = false
        if(item != null && qName.equals("x")) {
            ballonArray.add(item!!)
        }
    }

    fun getArray() : ArrayList<Balloon> {
        return ballonArray
    }
}