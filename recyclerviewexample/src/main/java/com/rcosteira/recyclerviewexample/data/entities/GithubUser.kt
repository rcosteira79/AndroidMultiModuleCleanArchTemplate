package com.rcosteira.recyclerviewexample.data.entities

import com.squareup.moshi.Json

data class GithubUser(
    @field:Json(name = "login") val login: String = "",
    @field:Json(name = "id") val id: Int = -1,
    @field:Json(name = "node_id") val nodeId: String = "",
    @field:Json(name = "avatar_url") val avatarUrl: String = "",
    @field:Json(name = "gravatar_id") val gravatarId: String = "",
    @field:Json(name = "url") val url: String = "",
    @field:Json(name = "html_url") val htmlUrl: String = "",
    @field:Json(name = "followers_url") val followersUrl: String = "",
    @field:Json(name = "following_url") val followingUrl: String = "",
    @field:Json(name = "gists_url") val gistsUrl: String = "",
    @field:Json(name = "starred_url") val starredUrl: String = "",
    @field:Json(name = "subscriptions_url") val subscriptionsUrl: String = "",
    @field:Json(name = "organizations_url") val organizationsUrl: String = "",
    @field:Json(name = "repos_url") val reposUrl: String = "",
    @field:Json(name = "events_url") val eventsUrl: String = "",
    @field:Json(name = "received_events_url") val receivedEventsUrl: String = "",
    @field:Json(name = "type") val type: String = "",
    @field:Json(name = "site_admin") val siteAdmin: Boolean = false
)
