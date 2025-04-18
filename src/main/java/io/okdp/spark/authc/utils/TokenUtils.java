/*
 *    Copyright 2024 tosit.io
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.okdp.spark.authc.utils;

import com.nimbusds.jwt.JWTClaimsSet;
import io.okdp.spark.authc.config.Constants;
import io.okdp.spark.authc.model.UserInfo;

/** Access Token utility methods */
public class TokenUtils implements Constants {

  /** Get oidc user info from a given access token */
  public static UserInfo userInfo(String accessToken) {
    String[] parts = accessToken.split("\\.");
    return JsonUtils.loadJsonFromString(
        new String(BASE64URL_DECODER.decode(parts[1])), UserInfo.class);
  }

  /** Get oidc user info from a given JWTClaimSet */
  public static UserInfo userInfo(JWTClaimsSet claimSet) {
    return UserInfo.fromJWTClaim(claimSet);
  }

  /** Upper case for first letter and lower case fo remaining letter */
  public static String capitalize(String str) {
    if (str == null) return str;
    if (str.length() <= 1) return str.toUpperCase();
    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
  }
}
