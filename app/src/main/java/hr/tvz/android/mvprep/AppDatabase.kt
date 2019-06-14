package hr.tvz.android.mvprep

import com.raizlabs.android.dbflow.annotation.Database
import com.raizlabs.android.dbflow.annotation.Migration
import com.raizlabs.android.dbflow.sql.migration.BaseMigration
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "MVPRepDB"
    const val VERSION: Int = 1
}

// Initial migration (creating database)
@Migration(version = 0, database = AppDatabase::class)
class DBCreation : BaseMigration() {

    override fun migrate(database: DatabaseWrapper) {
        Beers.insertBeers(getBeers())
    }

    private fun getBeers(): ArrayList<Beer> {
        // mock
        return arrayListOf<Beer>(
            Beer(
                "Canadian",
                "Molson'S Brewery Of Canada Limited",
                "5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60483_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Blue",
                "Labatt Breweries Ontario",
                "5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/94449_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Bud Light",
                "Labatt Breweries Ontario",
                "4%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60473_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Busch",
                "LABATT",
                "4.7%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60477_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Laker Ice",
                "The Brick Brewing Co",
                "5.5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60766_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Moosehead",
                "MOOSEHEAD BREWERIES LIMITED",
                "5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/69530_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Carling Ice",
                "MOLSON",
                "5.5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60744_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Grolsch Premium Lager",
                "Grolsche Bierbrouwerij B.V.",
                "5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60674_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Old Milwaukee",
                "Sleeman Brewing & Malting Co",
                "4.9%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60706_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Molson Dry",
                "Molson'S Brewery Of Canada Limited",
                "5.5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60543_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Rolling Rock Pale Lager",
                "Labatt Breweries Ontario",
                "4.5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/90780_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Creemore Lager",
                "CREEMORE SPRINGS BREWERY",
                "5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60495_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Lakeport Pilsener",
                "Lakeport Brewing Corporation",
                "5%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60762_l.png",
                "https://www.thebeerguy.ca"
            ),
            Beer(
                "Brava",
                "BRAVA BREWING COMPANY",
                "4.9%",
                "https://d9n7s0cf8h13p.cloudfront.net/images/products/60741_l.png",
                "https://www.thebeerguy.ca"
            )
        )
    }
}
