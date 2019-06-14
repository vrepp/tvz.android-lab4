package hr.tvz.android.mvprep

import android.os.Parcelable
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beer(
    val name: String,
    val brewer: String,
    val alcohol: String,
    val imageUrl: String,
    val url: String
) : Parcelable


@Table(name = BeerDBEntry.NAME, database = AppDatabase::class, allFields = true)
class BeerDBEntry : BaseModel {
    companion object {
        const val NAME = "BeerDBEntry"
    }
    @PrimaryKey(autoincrement = true)
    @Column
    var uid: Long = 0L
    @Column
    var name: String = ""
    @Column
    var brewer: String = ""
    @Column
    var alcohol: String = ""
    @Column
    var image_url: String = ""
    @Column
    var url: String = ""

    constructor() {
        // Mandatory for DBFlow
    }

    constructor(beer: Beer) {
        beer.let {
            name = it.name
            brewer = it.brewer
            alcohol = it.alcohol
            image_url= it.imageUrl
            url = it.url
        }
    }

    fun toDomain(): Beer =
        Beer(
            name,
            brewer,
            alcohol,
            image_url,
            url
        )
}

object Beers {

    fun getBeers(): List<Beer> {
        return SQLite.select()
            .from(BeerDBEntry::class.java)
            .queryList()
            .map { it.toDomain() }
    }

    fun insertBeer(beer: Beer) {
        BeerDBEntry(beer).save()
    }

    fun insertBeers(beers: List<Beer>) {
        FlowManager.getDatabase(AppDatabase.javaClass)
            .beginTransactionAsync {
                beers.forEach {
                    BeerDBEntry(it).save()
                }
            }
            .build()
            .execute()
    }

    fun deleteAllBeers() {
        SQLite.delete(BeerDBEntry::class.java)
            .async()
            .execute()
    }
}