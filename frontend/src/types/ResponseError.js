/**
 * @typedef {Object} ResponseError
 * @property {number} errorCode
 * @property {string} errorMessage
 * @property {Date} timestamp
 */

/**
 * 
 * @param {import('axios').AxiosError} error  
 * @returns {ResponseError}
 */
export function getResponseError(error) {
    return error.response.data;
};