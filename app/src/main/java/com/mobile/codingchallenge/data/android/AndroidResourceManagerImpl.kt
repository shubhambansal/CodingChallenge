package glotto.mobile.cashbook.data.android

import android.content.Context
import com.mobile.codingchallenge.data.AndroidResourceManager

/**
 * Use this class for accessing android system specific resources.
 */
class AndroidResourceManagerImpl constructor(private val context: Context) : AndroidResourceManager {

    override fun getString(id: Int): String {
        return context.getString(id)
    }
}