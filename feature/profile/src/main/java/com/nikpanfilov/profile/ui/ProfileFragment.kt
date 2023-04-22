package com.nikpanfilov.profile.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.profile.R
import com.nikpanfilov.profile.databinding.DialogImageSorceBinding
import com.nikpanfilov.profile.databinding.DialogImageUrlBinding
import com.nikpanfilov.profile.databinding.FragmentProfileBinding
import com.nikpanfilov.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.time.LocalDate

class ProfileFragment : Fragment() {

	companion object {

		const val GALLERY_REQUEST = "image/*"
		const val CAMERA_REQUEST = 1

		fun newInstance() = ProfileFragment()
	}

	private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ProfileViewModel>()

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)

		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)

		val imageSourceBinding = DialogImageSorceBinding.inflate(layoutInflater)
		DialogImageUrlBinding.inflate(layoutInflater).bindData(viewModel)

		binding.changeAvatarButton.setOnClickListener {
			val dialog = Dialog(requireContext())
			dialog.setContentView(imageSourceBinding.root)
			imageSourceBinding.fromInternetButton.setOnClickListener {
				AlertDialog.Builder(context).setView(R.layout.dialog_image_url).show()
			}

			imageSourceBinding.fromStorageButton.setOnClickListener {
				openGallery()
//				if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//					openGallery()
//				} else {
//					requestGalleryPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//				}
			}

			imageSourceBinding.fromCameraButton.setOnClickListener {
				openCamera()
			}

			dialog.show()
		}
		return binding.root
	}

	private val pickPictureLauncher: ActivityResultLauncher<String> =
		registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
			if (uri != null) {
				val byteArray = requireContext().contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }
				val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray?.size ?: 0)
				viewModel.saveAvatar(bitmap)
			}
		}

	private fun openGallery() {
		pickPictureLauncher.launch(GALLERY_REQUEST)
	}

	private fun openCamera() {
		val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
		val photoFile = createImageFile()
		val photoURI = FileProvider.getUriForFile(requireContext(), "com.nikpanfilov.worldcinema", photoFile)
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
		takePhotoResultLauncher.launch(intent)
	}

	private val takePhotoResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
		if (result.resultCode == Activity.RESULT_OK) {
			//viewModel.saveAvatar(result.data.data)
		}
	}

	private fun createImageFile(): File {
		val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
		return File.createTempFile("JPEG_${LocalDate.now()}", ".jpg", storageDir)
	}

	private fun showPickPhotoDialog() {
		val photoPickerIntent = Intent(Intent.ACTION_PICK)
		photoPickerIntent.type = "image/*"
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				val imageUri = result.data?.data
				viewModel.saveAvatar(MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri))
			}
		}.launch(photoPickerIntent)
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)

		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}