package com.example.dev.southbrmemes.Model.BussnesRule

/**
 * Created by dev on 13/05/2018.
 */
class Thred{
    companion object {
        public fun start(r: () -> Unit){
            var t: Thread = Thread(r);
            t.start()
            t.join()
        }
    }
}