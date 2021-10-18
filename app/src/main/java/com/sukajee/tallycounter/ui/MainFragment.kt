package com.sukajee.tallycounter.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sukajee.tallycounter.R
import com.sukajee.tallycounter.TallyEvents
import com.sukajee.tallycounter.databinding.FragmentMainBinding
import exhaustive
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Sushil Kafle on 16,October,2021
 */

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)

        if(savedInstanceState != null) {
            lifecycleScope.launch {
                viewModel.handleTargetReach()
            }
        }

        binding.apply {
            textViewCount.text = viewModel.currentCount.toString()
            viewModel.setTarget.also {
                if (it != Int.MAX_VALUE) {
                    textViewThisOfThis.apply {
                        visibility = View.VISIBLE
                        text = getString(R.string.target_string, it)
                    }
                }
            }
            viewModel.resetVisible.observe(viewLifecycleOwner::getLifecycle) { resetVisible ->
                binding.buttonReset.visibility = if (resetVisible) View.VISIBLE else View.GONE
            }
            buttonPlus.setOnClickListener {
                viewModel.onPlusClick()
            }
            buttonMinus.setOnClickListener {
                viewModel.onMinusClick()
            }
            buttonReset.setOnClickListener {
                viewModel.onResetClick()
            }
            buttonTarget.setOnClickListener {
                viewModel.onTargetClick()
            }
            buttonSteps.setOnClickListener {
                viewModel.onStepsClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tallyEvents.collect { event ->
                when (event) {
                    is TallyEvents.UpdateCount -> {
                        binding.textViewCount.text = event.currentCount.toString()
                    }
                    is TallyEvents.NotifyMaxCountReached -> {
                        val message = getString(R.string.reached_max_allowed, MAX_COUNT)
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
                    }
                    is TallyEvents.NotifyMinCountReached -> {
                        val message = getString(R.string.reached_max_allowed, -MAX_COUNT)
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
                    }
                    is TallyEvents.NotifyThisActionExceedsTarget -> {
                        if(event.isMax) {
                            val message = getString(R.string.this_action_exceeds_max_allowed, MAX_COUNT)
                            Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
                        } else {
                            val message = getString(R.string.this_action_exceeds_min_allowed, -MAX_COUNT)
                            Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                    is TallyEvents.ShowStepsEntryScreen -> {
                        val action = MainFragmentDirections.actionGlobalStepsEntryDialog()
                        findNavController().navigate(action)
                    }
                    is TallyEvents.ShowTargetEntryScreen -> {
                        val action = MainFragmentDirections.actionGlobalTargetEntryDialog()
                        findNavController().navigate(action)
                    }
                    is TallyEvents.ShowResetConfirmationScreen -> {
                        val action = MainFragmentDirections.actionGlobalResetConfirmationDialog()
                        findNavController().navigate(action)
                    }
                    is TallyEvents.ShowTargetCountDetails -> {
                        binding.textViewThisOfThis.apply {
                            if (event.target != Int.MAX_VALUE) {
                                visibility = View.VISIBLE
                                text = getString(R.string.target_string, event.target)
                            } else {
                                visibility = View.GONE
                            }
                        }
                    }
                    is TallyEvents.ShowHideTargetReachedCircle -> {
                        if (event.showGreenCircle) {
                            val circleDrawable = when (event.digits) {
                                1, 2 -> R.drawable.circle
                                3 -> R.drawable.circle3digit
                                4 -> R.drawable.circle4digit
                                5 -> R.drawable.circle5digit
                                else -> R.drawable.circle
                            }
                            binding.imageViewGreen.background =
                                ContextCompat.getDrawable(requireContext(), circleDrawable)
                            binding.imageViewGreen.visibility = View.VISIBLE
                        } else {
                            binding.imageViewGreen.visibility = View.GONE
                        }
                    }
                    is TallyEvents.NotifyTargetCanNotBeSet -> {
                        val message = getString(R.string.unable_to_set_target, event.target, MAX_COUNT, -MAX_COUNT)
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
                    }
                }.exhaustive
            }
        }
    }
}