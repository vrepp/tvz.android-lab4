/**
 * Beers Router
 * 
 * @author Valentin Rep
 * @copyright © 2019 ViCon. All rights reserved.
 * @version 1.0
 */

import * as express from 'express'

import { BeerHelper } from '../helpers'

export let router = express.Router()

/**
 * @api {get} /beers Retrieve User Chats 
 * @apiVersion 0.1.0
 * @apiName BeersGet
 * @apiGroup Beers
 * 
 * @apiSuccess {Object[]} beers list
 * @apiSuccess {String} beers.name Name
 */
router.get('/', async (request, response) => {
    try {
        const beers = await BeerHelper.getBeers()

        console.log("[get] /beers: ", beers)
        return response
            .status(200)
            .type('application/json')
            .send({beers: beers})
    } catch (error) {
        console.error("[get] /beers: ", error)
        return response
            .status(422)
            .type('application/json')
            .send({error: error})
    }
})

/**
 * @api {post} /beers Create a beer
 * @apiVersion 0.1.0
 * @apiName BeersPost
 * @apiGroup Beers
 * 
 * @apiParam {String} name Name
 * @apiParam {String} brewer Brewer
 * @apiParam {String} alcohol Alcohol
 * @apiParam {String} imageUrl Image URL
 * @apiParam {String} url URL
 * 
 * @apiSuccess {String} beerId Beer ID
 */
router.post('/', async (request, response) => {
    const { name, brewer, alcohol, imageUrl, url } = request.body

    try {
        const beer = await BeerHelper.createBeer(name, brewer, alcohol, imageUrl, url)

        console.log("[post] /beers: ", beer.beerId)
        return response
            .status(200)
            .type('application/json')
            .send({beerId: beer.beerId});
    } catch (error) {
        console.error("[post] /beers: ", error)
        return response
            .status(500)
            .type('application/json')
            .send({error: error});
    }
})

/**
 * @api {delete} /beers/:beerId Delete a beer
 * @apiVersion 0.2.0
 * @apiName BeersDelete
 * @apiGroup Beers
 * 
 * @apiParam {String} beerId Beer ID
 * 
 * @apiSuccess {String} beerId Beer ID
 */
router.delete('/:beerId', async (request, response) => {
    const { beerId } = request.params

    try {
        await BeerHelper.deleteBeer(beerId)
        
        console.log("[delete] /beers: ", beerId)
        return response
            .status(200)
            .type('application/json')
            .send({beerId: beerId})
    } catch (error) {
        console.error("[delete] /beers: ", error)
        return response
            .status(500)
            .type('application/json')
            .send({error: error});
    }    
})

/**
 * Return error 404 if Route is not found
 */
router.all("*", async (request, response) => {
    response
        .status(404)
        .send("404: Route not found")
})
