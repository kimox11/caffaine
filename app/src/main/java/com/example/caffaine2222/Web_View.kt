package com.example.caffaine2222

import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class Web_View : AppCompatActivity() {
    lateinit var webview: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webview = findViewById(R.id.Wv_view)
        var webHtml = "\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>sign-uP</title>\n" +
                "</head>\n" +
                "<body style=\"background-color: green\">\n" +
                "        <div id=\"home\" >\n" +
                "        <h1 style=\"color: white\">Register Now</h1>\n" +
                "         \n" +
                "            <input type=\"text\" id=\"firstname\" name=\"first-name\" placeholder=\"Your name\">\n" +
                "            <br><br>\n" +
                "             <input type=\"submit\" class=\"\" onclick=\"myFunction()\">\n" +
                "\n" +
                "        </div> \n" +
                "         <div id = \"wel\" class=\"hide\">\n" +
                "             <h1 id = \"welname\" style=\"color: white\"></h1>\n" +
                "         </div>\n" +
                "         <div id=\"back\" class=\"hide\">\n" +
                "              <input type=\"submit\" value=\"Back\"   onclick=\"myFunction1()\">\n" +
                "         </div>\n" +
                "\n" +
                "</body>\n" +
                "<style type=\"text/css\">\n" +
                "     .hide{\n" +
                "        display: none;\n" +
                "     }\n" +
                "</style>\n" +
                "<script type=\"text/javascript\">\n" +
                "    var name = document.getElementById(\"firstname\");\n" +
                "    var home = document.getElementById(\"home\");\n" +
                "    var wel = document.getElementById(\"wel\");\n" +
                "    var welname = document.getElementById(\"welname\");\n" +
                "    var back = document.getElementById(\"back\");\n" +
                "    var txtboxname = name.value+\"\";\n" +
                "\n" +
                "    function myFunction() {\n" +
                "    var name = document.getElementById(\"firstname\");\n" +
                "    var txtboxname = name.value+\"\";\n" +
                "    welname.innerHTML = \"welcome \" + txtboxname;\n" +
                "    home.classList.add (\"hide\");\n" +
                "    wel.classList.remove (\"hide\");\n" +
                "    back.classList.remove (\"hide\");\n" +
                "    }\n" +
                "\n" +
                "    function myFunction1() {\n" +
                "    back.classList.add (\"hide\");\n" +
                "    home.classList.remove (\"hide\");\n" +
                "    wel.classList.add (\"hide\");\n" +
                "    }\n" +
                "    \n" +
                "</script>\n" +
                "</html>\n"

        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true
        val encodedHtml: String = Base64.encodeToString(
            webHtml.toByteArray(),
            Base64.NO_PADDING
        )
          webview.loadData(encodedHtml, "text/html", "base64")
//        webview.loadUrl("https://translate.google.com/")
    //       webview.loadUrl("https://www.youtube.com/")


    }

    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webview.canGoBack())
            webview.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
}