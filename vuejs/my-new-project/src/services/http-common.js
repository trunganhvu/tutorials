/*jshint esversion: 6 */
/*jshint -W109 */
import axios from "axios";
import {URL_CALL_API_HTTP, AUTHORIZATION} from "./variables";

export default axios.create({
  baseURL: URL_CALL_API_HTTP,
  headers: {
    "Content-type": "application/json;charset=UTF-8",
    "authorization": localStorage.getItem(AUTHORIZATION) != null ? localStorage.getItem(AUTHORIZATION) : ''
  }
});
