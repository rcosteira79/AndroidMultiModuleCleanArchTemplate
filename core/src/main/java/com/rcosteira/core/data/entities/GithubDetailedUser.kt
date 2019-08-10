package com.rcosteira.core.data.entities

import com.squareup.moshi.Json

data class GithubDetailedUser(
    @field:Json(name = "login") val login: String = "",
    @field:Json(name = "value") val id: Long = -1,
    @field:Json(name = "node_id") val nodeId: String = "",
    @field:Json(name = "avatar_url") val avatarUrl: String = "",
    @field:Json(name = "gravatar_id") val gravatarId: String = "",
    @field:Json(name = "url") val url: String = "",
    @field:Json(name = "html_url") val htmlUrl: String = "",
    @field:Json(name = "followers_url") val followersUrl: String = "",
    @field:Json(name = "following_url") val followingUrl: String = "",
    @field:Json(name = "gists_url") val gistsUrl: String = "",
    @field:Json(name = "starred_url") val starredUrl: String = "",
    @field:Json(name = "subscriptions_url") val subscritionsUrl: String = "",
    @field:Json(name = "organizations_url") val organizationsUrl: String = "",
    @field:Json(name = "repos_url") val reposUrl: String = "",
    @field:Json(name = "events_url") val eventsUrl: String = "",
    @field:Json(name = "received_events_url") val receivedEventsUrl: String = "",
    @field:Json(name = "type") val type: String = "",
    @field:Json(name = "site_admin") val isSiteAdmin: Boolean = false,
    @field:Json(name = "name") val name: String = "",
    @field:Json(name = "company") val company: String = "",
    @field:Json(name = "blog") val blog: String = "",
    @field:Json(name = "location") val location: String = "",
    @field:Json(name = "email") val email: String = "",
    @field:Json(name = "hireable") val hireable: Boolean = false,
    @field:Json(name = "bio") val bio: String = "",
    @field:Json(name = "public_repos") val publicRepos: Long = 0,
    @field:Json(name = "public_gists") val publicGists: Long = 0,
    @field:Json(name = "followers") val followers: Long = 0,
    @field:Json(name = "following") val following: Long = 0,
    @field:Json(name = "created_at") val createdAt: String = "",
    @field:Json(name = "updated_at") val updatedAt: String = ""
)