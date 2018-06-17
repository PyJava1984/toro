/*
 * Copyright (c) 2018 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package toro.pixabay.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import toro.pixabay.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, MainFragment.Callback {

  companion object {
    var currentPosition = -1
    var currentTop = -1
    const val EXTRA_CURRENT_POSITION = "toro.pixabay:extra:current_position"
    const val EXTRA_CURRENT_POSITION_TOP = "toro.pixabay:extra:current_position_top"
  }

  override fun onSearchQuery(query: String) {
    title = "Query: $query"
  }

  @Inject
  lateinit var injector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = injector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    // setSupportActionBar(toolbar)

    var mainFragment = supportFragmentManager.findFragmentById(
        R.id.fragmentContainer) as MainFragment?

    if (mainFragment == null) {
      mainFragment = MainFragment.newInstance()
      supportFragmentManager.beginTransaction()
          .add(R.id.fragmentContainer, mainFragment, MainFragment::class.java.simpleName)
          .commit()
    }

//    toolbar.setOnTouchListener { _, _ ->
//      mainFragment.scrollToTop()
//      true
//    }
  }

  override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
    super.onSaveInstanceState(outState, outPersistentState)
    outState.putInt(EXTRA_CURRENT_POSITION, currentPosition)
    outState.putInt(EXTRA_CURRENT_POSITION_TOP, currentTop)
  }
}
