package com.blog.blogapplication.config;

/**
 * This class contains constants used throughout the application.
 */
public class AppConstants {

  /** Default page number for pagination. */
  public static final String PAGE_NUMBER = "0";

  /** Default page size for pagination. */
  public static final String PAGE_SIZE = "5";

  /** Default field to sort by for pagination. */
  public static final String SORT_BY = "postId";

  /** Default sorting order for pagination. */
  public static final String ORDER = "ascending";

  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
}
