/**
 * Beers API
 * 
 * API for TVZ Android lab04
 * 
 * @author Valentin Rep
 * @copyright © 2019 ViCon. All rights reserved.
 * @version 1.0
 */

import * as functions from 'firebase-functions'
import * as admin from 'firebase-admin'
import * as express from 'express'
import * as bodyParser from 'body-parser'
import * as cors from 'cors'

import { loggerMiddleware } from './middleware'

// The Firebase Admin SDK
admin.initializeApp()

// Express
const app = express()

// https://expressjs.com/en/advanced/best-practice-security.html#at-a-minimum-disable-x-powered-by-header
app.disable("x-powered-by");

// Automatically allow cross-origin requests
app.use(cors({ origin: true }))

// Parse application/json
app.use(bodyParser.json())

// Middleware
app.use(loggerMiddleware)

///////////// API v1 /////////////

// Routes
import * as beersApi from './api/beers'

// Express
const api_v2 = express()

// Set Routes for /v2
api_v2.use('/beers', beersApi.router)
app.use('/v2', api_v2)

// Bind functions to /api
export const api = functions.https.onRequest(app);