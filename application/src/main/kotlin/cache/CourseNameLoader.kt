package cache

import kotlinx.coroutines.runBlocking
import logic.ktorClient.queryAllClasses
import java.util.concurrent.CompletableFuture

object CourseNameLoader {
    private val allCourseNames: CompletableFuture<List<String>> = CompletableFuture.supplyAsync { runBlocking {
        queryAllClasses()
    }}

    fun cacheAllCourseNames() {
        allCourseNames.complete(runBlocking {
            queryAllClasses()
        })
    }

    fun getAllCourseNames(): List<String> {
        return allCourseNames.get() // blocks until futureCourses is done
    }
}
