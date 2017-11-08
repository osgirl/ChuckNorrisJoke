package uk.co.nbrown.cnjoke.RafaelRuiz.helpers

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by rafaelruizmunoz on 08/11/2017.
 */

object ViewHelpers {
    fun closeKeyboard(view: View?) {
        if (view != null) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
