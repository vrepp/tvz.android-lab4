/**
 * Beer Helper
 * 
 * @author Valentin Rep
 * @copyright © 2019 ViCon. All rights reserved.
 * @version 1.0
 */

import * as admin from 'firebase-admin'
import { Beer } from '../structs'

const beersCollection = 'beers'
const beersRef = admin.database().ref().child(beersCollection)

export class BeerHelper {
    /**
     * Fetch all Beers
     */
    static async getBeers() {
        const beers = new Array<Beer>()

        try {
            const snap = await beersRef.orderByKey().once('value')
            snap.forEach((childSnap) => {
                beers.push(childSnap.val() as Beer)
                return false
            })
        } catch (error) { 
            throw error
        }

        return beers
    }

    /**
     * Creating Beer
     * 
     * @param name Name
     * @param brewer Brewer
     * @param alcohol Alcohol
     * @param imageUrl Image URL
     * @param url URL
     */
    static async createBeer(name: string, brewer: string, alcohol: string, imageUrl: string, url: string) {
        const beer: Beer = {
            beerId: '',
            name: name,
            brewer: brewer,
            alcohol: alcohol,
            imageUrl: imageUrl,
            url: url
        }

        try {
            const newBeerRef = await beersRef.push()
            const beerId = newBeerRef.key
            beer.beerId = beerId

            console.log('Creating Beer: %s', beerId)
            await beersRef.child(beerId).set(beer)
        } catch (error) {
            console.error('Creating Beer: ', error)
            throw error
        }

        return beer
    }

    /**
     * Delete Beer
     * 
     * @param beerId Beer ID
     */
    static async deleteBeer(beerId: string) {
        try {    
            await beersRef.child(beerId).remove()
            console.log('Deleting Beer: %s', beerId)
        } catch (error) {
            console.error('Deleting Beer: ', error)
            throw error
        }
    }
}