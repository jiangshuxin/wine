const express = require('express');
const router = express.Router();

router.use('/user', (req, res) => res.status(200));


module.exports = router;
