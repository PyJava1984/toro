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

package toro.pixabay.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import toro.pixabay.data.entity.PhotoSearchResult
import toro.pixabay.data.entity.VideoSearchResult

/**
 * @author eneim (2018/05/02).
 */
interface Api {

  companion object {
    const val BASE_URL = "https://pixabay.com"
  }

  @GET("/api?image_type=photo")
  fun searchPhoto(
      @Query("q") query: String,
      @Query("page") page: Int,
      @Query("per_page") perPage: Int
  ): Call<PhotoSearchResult>

  @GET("/api/videos/?video_type=film")
  fun searchVideo(
      @Query("q") query: String,
      @Query("page") page: Int,
      @Query("per_page") perPage: Int
  ): Call<VideoSearchResult>
}