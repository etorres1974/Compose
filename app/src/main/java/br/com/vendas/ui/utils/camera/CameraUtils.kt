package br.com.vendas.ui.utils.camera

import android.provider.MediaStore.Images

import android.graphics.Bitmap

import android.content.ContentUris

import android.provider.MediaStore

import android.content.ContentValues

import android.content.ContentResolver
import android.graphics.Matrix
import java.io.FileNotFoundException
import java.io.IOException

fun insertImage(
    cr: ContentResolver,
    source: Bitmap?,
    title: String?,
    description: String?
): String? {
    val values = ContentValues()
    values.put(Images.Media.TITLE, title)
    values.put(Images.Media.DISPLAY_NAME, title)
    values.put(Images.Media.DESCRIPTION, description)
    values.put(Images.Media.MIME_TYPE, "image/jpeg")
    values.put(Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
    values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis())
    var stringUrl: String? = null
        cr.insert(Images.Media.EXTERNAL_CONTENT_URI, values)?.let { url ->
            if (source != null) {
                cr.openOutputStream(url)?.let { imageOut ->
                    imageOut.use { imageOut ->
                        source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut)
                    }
                    val id = ContentUris.parseId(url)
                    val miniThumb = Images.Thumbnails.getThumbnail(cr, id, Images.Thumbnails.MINI_KIND, null)
                    storeThumbnail(cr, miniThumb, id, 50f, 50f, Images.Thumbnails.MICRO_KIND)
                }
            } else {
                cr.delete(url, null, null)
            }
            stringUrl = url.toString()
        }
    return stringUrl
}

private fun storeThumbnail(
    cr: ContentResolver,
    source: Bitmap,
    id: Long,
    width: Float,
    height: Float,
    kind: Int
): Bitmap? {

    // create the matrix to scale it
    val matrix = Matrix()
    val scaleX = width / source.width
    val scaleY = height / source.height
    matrix.setScale(scaleX, scaleY)
    val thumb = Bitmap.createBitmap(
        source, 0, 0,
        source.width,
        source.height, matrix,
        true
    )
    val values = ContentValues(4)
    values.put(Images.Thumbnails.KIND, kind)
    values.put(Images.Thumbnails.IMAGE_ID, id.toInt())
    values.put(Images.Thumbnails.HEIGHT, thumb.height)
    values.put(Images.Thumbnails.WIDTH, thumb.width)
    val url = cr.insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, values)
    return try {
        val thumbOut = cr.openOutputStream(url!!)
        thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut)
        thumbOut!!.close()
        thumb
    } catch (ex: FileNotFoundException) {
        null
    } catch (ex: IOException) {
        null
    }
}
