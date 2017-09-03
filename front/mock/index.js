const express = require('express');
const router = express.Router();
const mdseController = require('./mdse.controller');
const merchantController = require('./merchant.controller');
const api = '/wine/api/front';

const response =(req, res) => {
    res.status(200).json(Object.assign({}, res.__data, {errorMsg: null, success: true}));
}

router.post(`${api}/getMdses`, mdseController.getMdses, response);
router.get(`${api}/getMdseDetail/:id`, mdseController.getMdseDetail, response);
router.get(`${api}/getMerchantInfo/:id`, merchantController.getMerchantInfo, response);


module.exports = router;
