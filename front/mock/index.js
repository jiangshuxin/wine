const express = require('express');
const router = express.Router();
const mdseControlelr = require('./mdse.controller');
const api = '/wine/api/front';

const response =(req, res) => {
    res.status(200).json(Object.assign({}, res.__data, {errorMsg: null, success: true}));
}

router.post(`${api}/getMdses`, mdseControlelr.getMdses, response);
router.get(`${api}/getMdseDetail/:id`, mdseControlelr.getMdseDetail, response);


module.exports = router;
